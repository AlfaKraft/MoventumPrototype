
<template>
  <div>
  <b-container v-if="showForm" class="page-new-schema">
    <FormCreateSchema :getFieldValue="getFieldValues"/>
  </b-container>
    <Schema v-else :width="width" :height="height"/>
  </div>
</template>

<script>
  import FormCreateSchema from "../components/FormCreateSchema";
  import Schema from "../pages/schema"

  export default {
    components: {FormCreateSchema, Schema},
      data(){
          return {
              width: 0,
              height: 0,
              showForm: true
          }
      },
      methods: {
        getFieldValues(width, height){
            this.width = parseInt(width);
            this.height = parseInt(height);
            this.showForm = false;
        }
      }
  }
</script>

<style lang="scss">
.page-new-schema{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
}

</style>
