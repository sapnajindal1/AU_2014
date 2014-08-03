var i=-1;
var max=0;
var jsonObj=null;
function loadNext()
{
	if(i>=max-1)
		{
		alert("No more Images");
		
		}
	else
		{
		i++;
		document.getElementById("img1").src =jsonObj.images[i].url;
        document.getElementById("Name").innerHTML =  jsonObj.images[i].name;
        document.getElementById("info").innerHTML = jsonObj.images[i].info;
        document.getElementById("ref").innerHTML = jsonObj.images[i].ref;
		
		}
	
	}
function loadPrevious()
{
	if(i<1)
		{
		alert("No more Images");
		
		}
	else
		{
		i--;
		document.getElementById("img1").src =jsonObj.images[i].url;
        document.getElementById("Name").innerHTML =  jsonObj.images[i].name;
        document.getElementById("desc").innerHTML = jsonObj.images[i].desc;
		
		}
	
	}


function loadJSON()
{
   var data_file = "JSON/images.json";
   var http_request = new XMLHttpRequest();
   try{
      // Opera 8.0+, Firefox, Chrome, Safari
      http_request = new XMLHttpRequest();
   }catch (e){
      // Internet Explorer Browsers
      try{
         http_request = new ActiveXObject("Msxml2.XMLHTTP");
      }catch (e) {
         try{
            http_request = new ActiveXObject("Microsoft.XMLHTTP");
         }catch (e){
            // Something went wrong
            alert("Your browser broke!");
            return false;
         }
      }
   }
   http_request.onreadystatechange  = function(){
      if (http_request.readyState == 4 && http_request.status==200 )
      {
        // Javascript function JSON.parse to parse JSON data
         jsonObj = JSON.parse(http_request.responseText);
        max=jsonObj.images.length;
        i++;

        // jsonObj variable now contains the data structure and can
        // be accessed as jsonObj.name and jsonObj.country.
        document.getElementById("img1").src =jsonObj.images[i].url;
        document.getElementById("Name").innerHTML =  jsonObj.images[i].name;
        document.getElementById("desc").innerHTML = jsonObj.images[i].desc;
      }
   }
   http_request.open("GET", data_file, true);
   http_request.send();
}