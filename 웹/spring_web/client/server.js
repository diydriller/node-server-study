const http=require('http');
const fs=require('fs').promises;

http
    .createServer(async(req,res)=>{
        try{
            if(req.url=="/"){
                const data=await fs.readFile("./index.html");
                res.writeHead(200);
                res.end(data);
            }
        }
        catch(error){
            console.log(error);
            res.writeHead(500);
            res.end(error.message);
        }
    })
    .listen(3000,()=>{console.log("3000 port listening")});