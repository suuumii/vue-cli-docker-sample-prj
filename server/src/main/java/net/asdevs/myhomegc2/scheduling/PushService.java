package net.asdevs.myhomegc2.scheduling;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import net.asdevs.myhomegc2.repository.GetEventMapper;
import net.asdevs.myhomegc2.repository.entity.GetEventThisDayInEntity;
import net.asdevs.myhomegc2.repository.entity.GetEventThisDayOutEntity;
import net.asdevs.myhomegc2.scheduling.entity.pushNotificationEntity;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
public class PushService {

    private final GetEventMapper getEventMapper;

    private static final String API_KEY = "";
    private static final String APP_NO = "";
    private static final String MAIN_PATH = "https://api.push7.jp/api/v1/";
    private static final String POST_PATH = "/send";

    @Scheduled(cron = "0 0 20 * * *", zone = "Asia/Tokyo")
    public void pushBeforeDay() throws Exception {
        System.out.println("beforeDay");

        // 明日の日付を取得
        LocalDate nowDate = LocalDate.now().plusDays(1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatNowDate = dtf.format(nowDate);
        
        // ゴミ分別を取得
        String gcName = selectDateGcName(formatNowDate);

        // Push通知文面作成
        String body = generatePushDetails(gcName, false);

        String title = "こんばんは";

        callPush7Api(title, body);

    }

    // @Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
    @Scheduled(cron = "0 0 7 * * *", zone = "Asia/Tokyo")
    public void pushThisDay() throws Exception {
        System.out.println("ThisDay");

        // 日付を取得
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatNowDate = dtf.format(nowDate);
        
        // ゴミ分別を取得
        String gcName = selectDateGcName(formatNowDate);

        // Push通知文面作成
        String body = generatePushDetails(gcName, true);

        String title = "おはようございます";

        callPush7Api(title, body);
    }

    private String selectDateGcName(String date) {
        // バリデーションチェック
        if (!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            throw new RuntimeException("dateのフォーマット(yyyy-MM-dd)が異なります");
        }

        GetEventThisDayInEntity inEntity = new GetEventThisDayInEntity();
        inEntity.setGcDate(date);

        GetEventThisDayOutEntity outEntity = getEventMapper.getEventThisDay(inEntity);

        if (outEntity == null) {
            return "";
        }

        return outEntity.getGcName();
    }

    private String generatePushDetails(String trash, Boolean thisDayFlg) {
        // Push通知内容セット
        StringBuilder sb = new StringBuilder();
        String day = thisDayFlg ? "今日" : "明日";
        sb.append(day); // 今日 or 明日
        sb.append("は");
        if(!StringUtils.hasText(trash)){
            sb.append("お休みです");
        } else {
            sb.append(trash);
            sb.append("の日です");
        }

        return sb.toString();
    }

    private void callPush7Api(String title, String body) throws Exception {

        String path = MAIN_PATH + APP_NO + POST_PATH;
        String url = "http://www.city.nanyo.yamagata.jp/up/files/siminkurasi/gomieisei/gomidasikata/R3/3course.pdf";

        // Http body
        pushNotificationEntity pushModel = createPush7PostBody(title, body, url);

        // Object to json String
        String json = toJsonString(pushModel);

        // Http header
        Map<String, String> httpHeaders = new LinkedHashMap<String, String>();

        // Push7 API Post
        String str = postJson(path, "UTF-8", httpHeaders, json);

        // Show Response body
        System.out.println("response body: " + str);
    }

    /**
     * Push7 Push通知API Request Body Entity作成
     * @param title Push通知タイトル
     * @param body Push通知内容
     * @param url Push通知リンク
     * @return
     */
    private pushNotificationEntity createPush7PostBody(String title, String body, String url) {
        pushNotificationEntity pushModel = new pushNotificationEntity();
        pushModel.setApikey(API_KEY);
        pushModel.setBody(body);
        pushModel.setIcon("https://push7.jp/notifycation_icon.png");
        pushModel.setTitle(title);
        pushModel.setUrl(url);

        return pushModel;
    }

    /**
     * 
     * @param obj // push通知内容
     * @return // Json文字列
     * @throws Exception
     */
    private String toJsonString(pushNotificationEntity obj) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        System.out.println(json);
        return json;
    }

    /**
     * Jsonをポストする処理
     * @param url
     * @param encoding // utf-8
     * @param headers 
     * @param jsonString // Request Body
     * @return // Http Response body
     * @throws IOException
     */
    private String postJson(String url, String encoding, Map<String, String> headers, String jsonString) throws IOException {
        final okhttp3.MediaType mediaTypeJson = okhttp3.MediaType.parse("application/json; charset=" + encoding);

        final RequestBody requestBody = RequestBody.create(mediaTypeJson, jsonString);

        final Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();

        final OkHttpClient client = new OkHttpClient.Builder()
                .build();
        final Response response = client.newCall(request).execute();
        final String resultStr = response.body().string();
        return resultStr;
    }
}
