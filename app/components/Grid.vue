<template>
  <div>

    <div>
      <b-row>
      <b-col>
        <b-button class="gridButton" id="playButton" v-on:click="startFromBeginning">Play</b-button>
      </b-col>
      <b-col>
        <div><b-button class="gridButton" id="formationButton" v-on:click="addFormation">Add formation</b-button></div>
      </b-col>
        <b-col>
          <div><b-button class="gridButton"  v-on:click="deleteAll" style="width: 100%">Delete All</b-button></div>
        </b-col>
      </b-row>

   </div>
    <b-row>
      <b-card-text>
        Field width: {{fieldWidth}} m    Field height: {{fieldHeight}} m   One square is {{squareInMeters}}x{{squareInMeters}}m
      </b-card-text>
    </b-row>
    <canvas id="grid" ref="canvas"></canvas>
    <Formations/>
  </div>
</template>
<script>
  import Formations from "../components/Formation";
    import ProgressBar from "../components/Progress-bar"

    import {mapState, mapMutations} from "vuex"
    import { fabric } from 'fabric'


    export class Dancer {
        constructor(positionX, positionY, id){
            this.id = id;
            this.positionX = positionX;
            this.positionY = positionY;
            this.radius = 8;
        }

    }

    export default {
        name: "Grid",
        components: {Formations},
        props: ['fieldWidth', 'fieldHeight'],
        data(){
            return{
                progress: false,
                squareInMeters: 1
            }
        },

        mounted(){
            const vm = this;
            vm.canvas = new fabric.Canvas('grid');
            let oneMeterInPixels = 0;
            if (this.fieldHeight <= 60 && this.fieldWidth <=60){
                oneMeterInPixels = 30;

            }
            else{
                oneMeterInPixels = 15;
                this.squareInMeters = 2;
            }
            vm.canvas.setWidth(this.fieldWidth*oneMeterInPixels);
            vm.canvas.setHeight(this.fieldHeight*oneMeterInPixels);
            vm.drawGrid(vm.canvas);
            vm.canvas.on('mouse:down', function (event) {
                console.log(event.absolutePointer.x, event.absolutePointer.y);
                const x = Math.round(event.absolutePointer.x / 30) * 30;
                const y = Math.round(event.absolutePointer.y / 30) * 30;
                console.log(x,y);
                if (vm.availableSpot(x,y)){
                    vm.addDancer(event);
                    vm.addObject(vm.canvas, event)
                }

            });
            vm.canvas.on('object:modified', function (event){
                const objectId = event.target.id;
                const object = vm.canvas.getActiveObject();
                console.log(object);
                const objectsNewPositionX = Math.round(event.target.left / 30) * 30;
                const objectsNewPositionY = Math.round(event.target.top / 30) * 30;
                object.left = objectsNewPositionX - object.radius;
                object.top = objectsNewPositionY - object.radius;
                object.setCoords();
                vm.changeDancersXAndY(objectId, objectsNewPositionX, objectsNewPositionY)

            });
        },
        computed: {
            dancers () {
                return this.$store.getters.getDancersList()
            },
            formations (){
                return this.$store.getters.getFormations()
            },
            fieldData (){
                return {height: this.$store.getters.getFieldHeigth(), width: this.$store.getters.getFieldWidth()}
            }
        },

        methods:{
            addDancer(event) {
                console.log("clicked");


                const x = Math.round(event.absolutePointer.x / 30) * 30;
                const y = Math.round(event.absolutePointer.y / 30) * 30;

                if (this.availableSpot(x,y)){
                    if (x === 0 || y === 0) {

                    }
                    else {
                        const dancer = new Dancer(x, y, this.$store.getters.getDancersList().length + 1);
                        this.$store.commit('add', dancer);
                    }
                }
                else{
                    console.log("spot taken");
                }


            },
            ...mapMutations({
                toggle: 'index/add'
            }),

            changeDancersXAndY(id, x, y){
                this.$store.commit('changeXAndY',{id: id, x: x, y: y });
            },
            ...mapMutations({
               toggle: 'index/changeXAndY'
            }),

            drawGrid(canvas) {
                let gridSquareSize = 30;
                let x;
                let line;
                const canvasWidth = canvas.getWidth();
                const canvasHeight = canvas.getHeight();
                let centerX = 0;
                let centerY = 0;
                for (x = gridSquareSize; x <= canvasWidth; x += gridSquareSize) {
                    let color = "black";
                    if (x === canvasWidth / 2){
                        color = "red";
                        centerX = x;
                    }
                    line = new fabric.Line([x, 0, x, canvasHeight], {
                        stroke: color,
                        selectable: false,
                    });
                    canvas.add(line)
                }
                let y;
                for (y = gridSquareSize; y <= canvasHeight; y += gridSquareSize) {
                    let color = "black";
                    if (y === canvasHeight / 2){
                        color = "red"
                        centerY = y
                    }
                    line = new fabric.Line([0, y, canvasWidth, y], {
                        stroke: color,
                        selectable: false,
                    });
                    canvas.add(line)

                }
                this.addCenter();
                canvas.renderAll();

            },

            addCenter(){
                let circle = new fabric.Circle({
                        radius: 4,
                        fill: "black"
                    }

                );
                let center = new fabric.Group([circle], {
                    top: this.canvas.getHeight()/2-circle.radius,
                    left: this.canvas.getWidth()/2-circle.radius,
                    selectable: false

                });
                this.canvas.add(center);
            },
            availableSpot(x,y){

                const dancers = this.$store.getters.getDancersList();
                for (let i = 0; i < dancers.length; i++) {
                    if (dancers[i].positionY - 10 <= y && y <= dancers[i].positionY + 10 && dancers[i].positionX - 10 <= x && x <= dancers[i].positionX + 10){
                        return false;
                    }
                }
                return true;
            },

            addFormation(){
                this.$store.commit('addFormation', this.$store.getters.getDancersList(), this.canvas.toDataURL());
            },
            ...mapMutations({
                toggle: 'index/addFormation'
            }),

            addObject(canvas, event){
                const radius = 8;
                const x = Math.round(event.absolutePointer.x / 30) * 30;
                const y = Math.round(event.absolutePointer.y / 30) * 30;
                const circle = new fabric.Circle({
                    id: this.$store.getters.getDancersList().length,
                    radius: radius,
                    fill: 'red',
                    left: x - radius,
                    top: y - radius,
                    lockScalingX: true,
                    lockScalingY: true,
                });
                circle.setControlsVisibility({
                    mt: false,
                    mb: false,
                    ml: false,
                    mr: false,
                    bl: false,
                    br: false,
                    tl: false,
                    tr: false,
                    mtr: false
                    }
                );
                circle.hasBorders = false;
                canvas.add(circle)

            },
            clearField(){
                const objects = this.canvas.getObjects("circle");
                for (let i = 0; i < objects.length; i++) {
                    this.canvas.remove(objects[i]);
                }
            },
            startFromBeginning() {
                const formations = this.$store.getters.getFormations();
                if (formations.length !== 0) {
                    this.progress = true;
                    const firstFormation = formations[0];
                    const objects = this.canvas.getObjects("circle");
                    for (let i = 0; i < objects.length; i++) {
                        this.canvas.remove(objects[i]);
                    }
                    for (let i = 0; i < firstFormation.dancersList.length; i++) {
                        const circle = new fabric.Circle({
                            id: firstFormation.dancersList[i].id,
                            radius: 8,
                            fill: 'red',
                            left: firstFormation.dancersList[i].positionX - firstFormation.dancersList[i].radius,
                            top: firstFormation.dancersList[i].positionY - firstFormation.dancersList[i].radius,
                            lockScalingX: true,
                            lockScalingY: true,
                        });
                        circle.setControlsVisibility({
                                mt: false,
                                mb: false,
                                ml: false,
                                mr: false,
                                bl: false,
                                br: false,
                                tl: false,
                                tr: false,
                                mtr: false
                            }
                        );
                        circle.hasBorders = false;
                        this.canvas.add(circle)
                    }

                    this.play(this.$store.getters.getFormations().slice(1))

                }

            }
            ,
            play(formations){
                if (formations.length !== 0){
                    const canvas = this.canvas;
                    const objects = canvas.getObjects('circle');
                    const formation = formations[0].dancersList;
                    for (const dancer of objects){
                        const newXposition = this.findXPostionFromFormation(dancer.id, formation)-8;
                        const newYposition = this.findYPostionFromFormation(dancer.id, formation)-8;
                        dancer.animate({'left': parseInt(newXposition), 'top': parseInt(newYposition)}, {
                            duration: 2000,
                            onChange: canvas.renderAll.bind(canvas),
                            onComplete: () => {
                                this.play(formations.slice(1));
                            },
                        });
                    }
                }
            },

            deleteAll(){
                this.clearField();
                this.$store.commit('delete');
            },
            ...mapMutations({
                toggle: 'index/delete'
            }),

            findXPostionFromFormation(id, formation){
                let dancer;
                for (dancer of formation){
                    if (dancer.id === id){
                        return dancer.positionX
                    }
                }
            },

            findYPostionFromFormation(id, formation){
                let dancer;
                for (dancer of formation){
                    if (dancer.id === id){
                        return dancer.positionY
                    }
                }
            }

        }
    }

</script>
<style>
  #grid{
    margin: auto;
    border: solid black 4px;
  }
  #playButton{
    width: 100%;
  }
  #formationButton{
    width: 100%;
  }
  .gridButton{
    margin: 2rem;
  }



</style>
