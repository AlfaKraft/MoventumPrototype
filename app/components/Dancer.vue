<template>
  <ul id="dancers">
    <li v-for="dancer in dancers" :key="dancer.id">
      <b-card>
        <b-row no-gutters>
          <b-col md="2">
            <span class="dancerIcon"></span>
          </b-col>
          <b-col md="6">
            <b-card-body>
                {{dancer.id}}. x: {{dancer.positionX}} y: {{dancer.positionY}}
            </b-card-body>
          </b-col>
        </b-row>

      </b-card>
    </li>

  </ul>
</template>

<script>
    export default {
        name: "Dancer",

        computed: {
            dancers () {
                return this.$store.getters.getDancersList()
            },
            formations (){
                return this.$store.getters.getFormations()
            }
        },
    }




</script>

<style scoped>
#dancers{
  list-style: none;
}
  .dancerIcon{
    height: 50px;
    width: 50px;
    background-color: crimson;
    border-radius: 50%;
    display: inline-block;
  }
</style>
