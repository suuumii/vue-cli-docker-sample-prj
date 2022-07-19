<template>
  <div>
    <v-sheet
      tile
      height="50"
      color="grey lighten-3"
      class="d-flex align-center"
    >
      <v-btn outlined small class="ma-4" @click="setToday"> 今日 </v-btn>
      <v-btn icon @click="$refs.calendar.prev()">
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-btn icon @click="$refs.calendar.next()">
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
      <v-toolbar-title>{{ title }}</v-toolbar-title>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        locale="ja-jp"
        :day-format="(timestamp) => new Date(timestamp.date).getDate()"
        :month-format="
          (timestamp) => new Date(timestamp.date).getMonth() + 1 + ' /'
        "
        v-model="value"
        :events="events"
        :event-color="getEventColor"
        @click:event="showEvent"
        @click:date="clickedDay"
        @change="getEvents"
      ></v-calendar>
    </v-sheet>
    <v-row>
      <v-col>
        <v-radio-group v-model="selectItemId" row class="ml-10">
          <v-radio
            v-for="(item, idx) in iconList"
            :key="idx"
            :value="item.id"
            :color="item.color"
          >
            <template #label>
              <div>
                {{ item.name }}
              </div>
            </template>
          </v-radio>
        </v-radio-group>
      </v-col>
      <v-col cols="3">
        <v-btn outlined small class="ma-4" @click="saveEvent"> 保存 </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import moment from "moment";
import _ from "lodash";

export default {
  data() {
    return {
      value: moment().format("yyyy-MM-DD"), // 現在日時
      iconList: [
        {
          id: 1,
          name: "可燃",
          color: "#DD72A1",
        },
        {
          id: 2,
          name: "不燃",
          color: "#6EB92B",
        },
        {
          id: 3,
          name: "プラ",
          color: "#F39700",
        },
        {
          id: 4,
          name: "資源",
          color: "#00AFEC",
        },
        {
          id: 5,
          name: "なし",
        },
      ],
      selectItemId: 1, // 選択済みアイコン
      events: [],
      beforeEventList: [],
    };
  },
  computed: {
    title() {
      return moment(this.value).format("yyyy/MM"); // 表示用文字列を返す
    },
  },
  created() {},
  watch: {
    events: {
      handler() {
        console.log(JSON.stringify(this.events));
      },
      deep: true,
    },
  },
  methods: {
    async getEvents() {
      // Loading
      this.$store.commit("setIsLoading", true);

      console.log("getEvents");
      let parameter = {
        date: this.value,
      };
      const res = await this.post("/get_event", parameter);
      if (res.status === 200) {
        this.events = res.data;
        this.beforeEventList = _.cloneDeep(this.events);
      }
      // Loading
      this.$store.commit("setIsLoading", false);
    },
    getEventColor(event) {
      // 表示するイベントの色
      let icon = this.iconList.find((i) => i.id === event.id);
      return icon.color;
    },
    setToday() {
      this.value = moment().format("yyyy-MM-DD");
    },
    showEvent({ event }) {
      // alert(`clicked ${event.name}`);
      console.log(event);
    },
    clickedDay({ date }) {
      this.addEvent(date, this.selectItemId);
    },
    addEvent(date, selectedGarbage) {
      let idx = this.events.findIndex((e) => e.start === date);

      if (this.events[idx] && selectedGarbage === 5) {
        // 削除
        console.log("削除");
        let newEvents = this.events.filter((_, index) => index !== idx);
        this.events = newEvents;
      } else if (this.events[idx]) {
        // 入力済みの場合、上書き
        console.log("上書き");
        let newEvent = this.createEventForCalendar(date, selectedGarbage);
        this.$set(this.events, idx, newEvent);
      } else if (selectedGarbage === 5) {
        // 未入力かつ「なし」選択の場合、処理不要
        console.log("不要");
        return;
      } else {
        // 未入力の場合、新規作成
        console.log("新規作成");
        let newEvent = this.createEventForCalendar(date, selectedGarbage);
        this.events.push(newEvent);
      }
    },
    createEventForCalendar(date, garbage) {
      // calendarに登録するeventの生成
      let name = this.iconList.find((i) => i.id === garbage).name;
      let event = {
        name: name,
        id: garbage,
        start: date,
        timed: false,
      };
      return event;
    },
    async saveEvent() {
      // Loading
      this.$store.commit("setIsLoading", true);

      let parameter = this.diffEventList();
      if (parameter && parameter.length > 0) {
        await this.post("/update_event", parameter);
      }

      // Loading
      this.$store.commit("setIsLoading", false);
    },
    diffEventList() {
      let saveEventList = [];

      let cnt = this.events.length;
      for (let i = 0; i < cnt; i++) {
        let tmpEvent = this.events[i];

        let beforeEvent = this.beforeEventList.find(
          (i) => i.start === tmpEvent.start
        );

        // 新規・更新の洗い出し
        if (beforeEvent && beforeEvent.id === tmpEvent.id) {
          // 画面表示時と同じ場合、処理不要
        } else if (beforeEvent) {
          saveEventList.push({
            date: tmpEvent.start,
            id: tmpEvent.id,
            procFlg: 2, // 更新
          });
        } else {
          saveEventList.push({
            date: tmpEvent.start,
            id: tmpEvent.id,
            procFlg: 1, // 追加
          });
        }
      }
      // 削除の洗い出し
      cnt = this.beforeEventList.length;
      for (let i = 0; i < cnt; i++) {
        let tmpEvent = this.beforeEventList[i];
        if (0 > this.events.findIndex((i) => i.start === tmpEvent.start)) {
          saveEventList.push({
            date: tmpEvent.start,
            id: tmpEvent.id,
            procFlg: 3, // 削除
          });
        }
      }
      return saveEventList;
    },
  },
};
</script>
<style scoped></style>
