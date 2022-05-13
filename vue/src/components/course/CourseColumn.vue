<template>
  <div class="course">
    <h2>{{ coursename }}</h2>
    <div class="curriculas">
      <div
        class="curricula"
        v-for="curricula in curriculas"
        v-bind:key="curricula.id"
        v-on:click="viewCurriculaDetails(curricula.id)"
      >
        <div class="header">
          <h2>{{curricula.id}} - {{ curricula.name }} </h2>
        </div>
        <div class="footer">
        <h3>{{ curricula.summary }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['coursename', 'curriculas', 'courseID'],
  methods: {
    viewCurriculaDetails(curriculaID) {
      this.$router.push(`/curricula/${curriculaID}`);
    },
  }
};
</script>

<style scoped>
.course {
  background-color: lightgray;
  padding: 0 20px 20px 20px;
}
.curricula {
  background: #fff;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}
.curricula:last-child {
  margin-bottom: 0px;
}
.curricula h3 {
  margin-top: 0px;
  font-size: 0.875rem;
}
.curricula .header {
  display: flex;
  justify-content: space-between;
}
.curricula .header img {
  width: 32px;
  align-self: flex-start;
}
.curricula .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.75rem;
}
.design {
  background-color: #e6fffa;
  color: #42c2f5;
}
.qa {
  background-color: #e6fffa;
  color: #42c2f5;
}

.feature {
  background-color: #e6fffa;
  color: #42c2f5;
}
</style>
