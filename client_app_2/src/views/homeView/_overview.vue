<template>
  <div>
    <v-row class="text-center my-10">
      <v-col cols="12">
        <v-img
          :src="require('@/assets/gcIcon.svg')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>
      <v-col cols="12">
        <h2>わが家のゴミ分別カレンダー</h2>
      </v-col>
      <v-col cols="12">
        <h3>説明</h3>
        <p>
          カレンダーに設定したゴミ取集日の、前日夜9時と、当日朝７時にPUSH通知を送信するアプリです
        </p>
      </v-col>
      <v-col cols="12">
        <h3>操作手順</h3>
        <v-row class="text-center">
          <v-col cols="12">
            PUSH通知を受け取りたい端末で以下ボタンを押下し、通知を受信する設定を行う（外部サイトへ飛びます）
          </v-col>
          <v-col>
            <div class="p7button" data-button-text="購読する"></div>
          </v-col>
          <v-col cols="12">
            <p>カレンダーにわが家のごみ収集日を設定する。</p>
            <p>
              →
              カレンダー下のラジオボタンで設定するごみの種類を選択し、カレンダーの日付をクリックで設定できます
            </p>
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="12">
        <h3>注意点</h3>
        <p>
          本アプリはPush通知を行うために、外部アプリ「Push7」を使用しています
        </p>
        <p>iOS端末はWebPush未対応のため、Push7アプリのインストールが必要です</p>
        <p>
          Push7に課金していないため、Push通知を送る端末は選べません。購読している全端末へ通知が送信されます。他人の端末へも通知が飛びます。
        </p>
      </v-col>
    </v-row>
  </div>
</template>
