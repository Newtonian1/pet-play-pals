<template>
<div class="register-play-date">
  <h1>Schedule a Play Date</h1>
  <schedule-play-date-form/>
</div>
  
</template>

<script>
import SchedulePlayDateForm from '../components/SchedulePlayDateForm.vue'
export default {
  components: { SchedulePlayDateForm },

}
</script>

<style scoped>
  .register-play-date {
    background-color: #EEF2FF;
    border-radius: 5px;
    /* possibly use for home page */
    /* background-color: #F7F7F7; */
    font-family: 'Montserrat', sans-serif;
  }
  h1 {
    text-align: center;
    padding-top: 20px;
  }
</style>