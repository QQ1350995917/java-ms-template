# ms-web-initializr
|业务|端口号|
|---|---|
|configure|11220|
|gateway|11221|
|registry|11222|
|account|11230| 
|article|11231| 
|typeface|11232| 
|etl|11234| 
|organization|11238| 
|search|11239| 
|access|11240| 
|logger|11250| 
|monitor|11260| 
|storageBO|11270| 
|schedule|11280|

http://patorjk.com/software/taag/#p=display&f=Doh

mvn clean package docker:build -Dmaven.test.skip=true -DpushImage
