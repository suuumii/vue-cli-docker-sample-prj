package net.asdevs.myhomegc2.scheduling.entity;

import lombok.Data;

@Data
public class pushNotificationEntity {
    // @NonNull
    // 128文字まで
    private String title;

    // @NonNull
    // 512文字まで
    private String body;

    // @NonNull
    // httpsである必要あり。512文字まで
    private String icon;

    // @NonNull
    // 512文字まで
    private String url;

    // @NonNull
    // アプリケーションのapikey
    private String apikey;
}
