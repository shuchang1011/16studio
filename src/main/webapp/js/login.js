window.onload=function(){
    var stu,tea;
    var stuClass,teaClass;
    stu = document.getElementById("stu");
    tea = document.getElementById("tea");
    stuClass = document.getElementById("student");
    teaClass = document.getElementById("teacher");
    stu.style.color = "#EF5B00";
    teaClass.style.display = "none";
    
    stu.onclick = function(){
        console.log("点击了学生"+stu.style.color);
        if(stu.style.color != "rgb(239, 91, 0)"){
            stu.style.color = "#EF5B00";
            tea.style.color = "#747774";
            teaClass.style.display = "none";
            stuClass.style.display = "block";
        }
    };
    tea.onclick = function(){
        console.log("点击了老师");
        if(tea.style.color != "rgb(239, 91, 0)"){
            stu.style.color = "#747774";
            tea.style.color = "#EF5B00";
            stuClass.style.display = "none";
            teaClass.style.display = "block";
        }
    };
}