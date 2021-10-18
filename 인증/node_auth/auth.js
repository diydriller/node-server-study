const app=require('express')();
const cookieParser=require('cookie-parser');
const { resolveSoa } = require('dns');
const session=require('express-session');
const jwt=require('jsonwebtoken');

const cookieConfig={
    httpOnly:true,
    maxAge:1000000,
    signed:true
};
app.use(cookieParser('cookie'));

app.use(session({
    secret:'session',    
    resave: false,
    saveUninitialized: true,
}))



//쿠기 생성  /  Set-Cookie 로 key=value가 client로 전달
app.get('/cookie-create',(req,res)=>{
    res.cookie('key','value',cookieConfig);
    res.send('cookie created');
})

//쿠키 확인  /  client로부터 받은 쿠키를 확인  /  서명된 쿠키는 signedCookie를 이용해야 한다
app.get('/cookie-check',(req,res)=>{
    console.log(req.signedCookies);
    res.send(req.signedCookies);
})



//세션 생성  /  Set-Cookie로 connect.sid={sessionId}가 client로 전달
app.get('/session-create',(req,res)=>{
    req.session.key="value";
    res.send("session created");
});

//세션 아이디 확인  /  client로부터 받은 쿠키의 sessionId값을 바탕으로 session에서 정보를 확인 
app.get('/session-check',(req,res)=>{
    console.log(req.session);
    res.send(req.session);
})


//토큰 생성후 발급  
//JWT는 검증방법을 base64로 인코딩한 header 
//인증정보를 base64로 인코딩한 payload
//header와 payload를 서명하고 base64로 인코딩한 signature 세 부분이 .으로 구분되어 저장된다.
app.get('/jwt-create',async(req,res)=>{
    const token = await jwt.sign(
        {key:"value"}, 
        "jwt", 
        {expiresIn:'365d'}
    );
    res.send(token);
});



//JWT 검증
app.get('/jwt-verify',async(req,res)=>{

    try{
        const token = req.headers['access-token'];

        //JWT verify promise
        const verifyPromise= new Promise(
            (resolve, reject) => {
                jwt.verify(token,'jwt', (err, verifiedToken) => {
                    if(err) reject(err);
                    resolve(verifiedToken)
                })
            }
        );

        const verifiedToken=await verifyPromise;
        console.log(verifiedToken);
        res.send(verifiedToken);
    }
    catch(err){
        console.log(err);
        res.send(`token verify error: ${err}`);
    }
});

app.listen(3000,()=>{console.log("server is listening on 3000 port")});
