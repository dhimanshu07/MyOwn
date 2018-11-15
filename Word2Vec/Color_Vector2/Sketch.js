let data;
let vectors;
let Rainbow;
let lines;

let rSlider,gSlider,bSlider;
let keys = [];
let colorSpans = [];


function preload(){
    data = loadJSON('xkcd.json');
    lines = loadStrings('Rainbow.txt');
    
}


function setup() {


    createCanvas(50,50); 
    rSlider = createSlider(0,255,0);
    gSlider = createSlider(0,255,0);
    bSlider = createSlider(0,255,0);
    rSlider.input(sliderChanged);
    gSlider.input(sliderChanged);
    bSlider.input(sliderChanged);
    
    
    vectors = processData(data);
    Rainbow = join(lines, ' ');
    let words = Rainbow.split(/\W+/);
  




    for (let word of words){
       
      let span =   createSpan(word);
      if(vectors[word]){
          let c = vectors[word];
          span.style('background-color', `rgb(${c.x},${c.y},${c.z})`)
          colorSpans.push(span);
          keys.push(word);

      }

      createSpan(' ');

  //  for (let i =0 ; i<words.length; i++){
    //     createSpan(words[i]);
      //   createSpan(' ')
   //


  //    Rainbow = join(lines, '');
   // console.log(Rainbow.length);

  // let words = Rainbow.split(/\W+/);

   //for (let i =0 ; i<words.length; i++){
     //  if (vectors.hasOwnProperty(words[i])){
       //    console.log(words[i]);
       //}
 //  }
 
    }
    console.log(keys);

    let avg = createVector(0,0,0);
    for(let key of keys){
        let v = vectors[key];
        avg.add(v);
    }
    avg.div(keys.length);
    let nearest = findNearest(avg);
    console.log(nearest);
    background(avg.x,avg.y,avg.z);


  
}



function sliderChanged(){

    let r = rSlider.value();
    let g = gSlider.value();
    let b = bSlider.value();
    console.log(r ,g, b);

    for(let span of colorSpans){
       let word = span.html();
        let v = vectors[word].copy();
        v.add(r, g, b);
        let nearest = findNearest(v);
        span.style('background-color', `rgb(${v.x},${v.y},${v.z})`)
        span.html(nearest);
    } 
    
}






 function processData() {
     let vectors = {};
     let colors = data.colors;
     for(let i=0; i<colors.length; i++)
     {
         let label = colors[i].color;
         let rgb = color(colors[i].hex);
         vectors[label] = createVector(red(rgb), green(rgb),blue(rgb));
      }

     return vectors;
 } 


 function findNearest(v){

    let keys = Object.keys(vectors); 
    

    keys.sort((a,b)=>{
        let d1 = distance(v , vectors[a]);
        let d2 = distance(v , vectors[b]);
        return d1-d2;
        
    }); 
  

    return keys[0];
 }

 function distance(v1, v2){

    return p5.Vector.dist(v1, v2);

 }

 