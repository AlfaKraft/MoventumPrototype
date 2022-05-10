
export class Dancer {
  constructor(positionX, positionY, id){
    this.id = id;
    this.positionX = positionX;
    this.positionY = positionY;
    this.radius = 8;
  }
}
export class Formation{
  constructor(id, dancersList, image){
    this.id = id;
    this.dancersList = dancersList;
    this.image = image;
  }
}
export const state = () => ({
  dancers: [],
  formations: [],
  fieldWidth:0,
  fieldHeight: 0
});

export const mutations = {
  add(state,dancer){
    state.dancers.push(dancer)
  },
  delete(state){
    state.dancers = [];
    state.formations = [];
  },
  changeXAndY(state, payload){
    const dancer = state.dancers.find(dancer => dancer.id === payload.id);
    dancer.positionX = payload.x;
    dancer.positionY = payload.y;
    console.log(state.fieldWidth, state.fieldHeight)
},
  addFormation(state, formation, image){

    const newFormation = new Formation(state.formations.length, [], image);
    for (let i = 0; i < formation.length; i++) {
      const position = formation[i];
      const copyDancer = new Dancer(position.positionX, position.positionY, position.id);
      newFormation.dancersList.push(copyDancer);
    }
    state.formations.push(newFormation);
  },
  setField(state, data){
    console.log(data);
    state.fieldHeight = data.height;
    state.fieldWidth = data.width;
  }
};

export const getters = {
  getDancersList: (state) => () => {
    return (state.dancers)
},
  getFormations: (state) => () => {
    return (state.formations)
  },
  getFieldWidth: (state) => () => {
    return (state.fieldWidth)
  },
  getFieldHeigth: (state) => () => {
    return (state.fieldHeight)
  }
};

