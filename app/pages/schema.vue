<template>
  <b-container fluid>
    <b-row>
      <b-col>
        <Grid :fieldWidth="width" :fieldHeight="height"/>
      </b-col>
      <b-col>
        <Dancers/>
      </b-col>
    </b-row>
  </b-container>

</template>
<script>
    import Grid from "../components/Grid";
    import Dancers from "../components/Dancer"

    export default {
        components: {Grid, Dancers},
        props: ['width', 'height'],

    }
</script>

<style>
</style>
