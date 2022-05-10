<template lang="pug">
  b-card.text-center#form-card.justify-content-center(bg-variant="info" text-variant="white")
    b-card-text().font-weight-bold Uus joonis
    b-form#form.d-flex.flex-column.justify-content-center()
      b-form-group
        b-form-input.mb-4(v-model="form.name" type="text" required placeholder="Nimi" )
        b-form-input.mb-4(v-model="form.width" type="text" placeholder="Väljaku laius" )
        b-form-input.mb-4(v-model="form.height" type="text" placeholder="Väljaku pikkus" )
        b-button(v-on:click="submit()") Loo joonis

</template>

<script>
    import {mapState, mapMutations} from "vuex"
export default {
  name: "FormCreateSchema",
    props: {
        getFieldValue: Function
    },
  data(){
    return{
      form:{
        name: '',
        width: '',
        height: ''
      }
    }
  },
  methods: {
    submit(){
      console.log("submit");
      this.$axios.$post('http://localhost:8080', {
        name: this.form.name,
          width: this.form.width,
          height: this.form.height
      }).then((result) => {
        console.log(result)
      });
      this.getFieldValue(this.form.width, this.form.height)
        //window.location.href="/schema"
    },
    setField(width, height){
        this.$store.commit('setField', {width: width, height:height});
    },
      ...mapMutations({
          toggle: 'index/setField'
      })
  }
}
</script>

<style lang="scss">
#form-card{
  background-image: linear-gradient(90deg, rgba(0,0,0,0.5) 0%, rgba(0,0,0,0.5) 100%), url("~assets/images/create-schema-background.jpg");
  background-position: center;
  height: 100%;
  width: 100%;
  #form{
    height: 100%;
  }
}
</style>
