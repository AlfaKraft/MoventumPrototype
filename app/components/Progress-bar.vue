<template>
  <div>
  <b-progress :max="max" height="2rem" :striped="true" show-progress>
    <b-progress-bar :value="value" variant="success">
      <h5 v-if="value < 180">Loading</h5>
    </b-progress-bar>
  </b-progress>
  </div>
</template>

<script>
    export default {
        name: "Progress-bar",
        data() {
            return {
                timer: 0,
                value: 0,
                max: 180
            };
        },
        props: ['progress'],
        mounted() {

            this.startTimer();
        },
        methods: {
            startTimer() {
                let vm = this;
                let timer = setInterval(function() {
                    vm.value += 1;
                    if (vm.value >= 180) clearInterval(timer);
                }, 100);
            }
        }
    }
</script>

<style scoped>

</style>
