<template>
  <ul id="formations">
    <li v-for="formation in formations" :key="formation.id">
      <b-card>
        <b-row no-gutters>
          <b-col md="2">
            <span class="dancerIcon"></span>
          </b-col>
          <b-col md="6">
            <b-card-body>
              Formation - {{formation.id}}
            </b-card-body>
          </b-col>
        </b-row>

      </b-card>
    </li>

  </ul>
</template>

<script>
    export default {
        name: "Formation",
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
  #formations{
    list-style: none;
  }
  .dancerIcon{
    height: 50px;
    width: 50px;
    background-color: darkblue;
    border-radius: 50%;
    display: inline-block;
  }
</style>
