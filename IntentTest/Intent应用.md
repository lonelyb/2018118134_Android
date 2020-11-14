## 实验目的：Intent应用
###显式Intent
先构建出一个Intent 传入FirstActively.this 作为上下文，传入SeconActivity.class作为目标活动，这样我们的“意图就很明显”  
运行程序，点击FirstActively界面的按钮，可以启动SecondActively这个活动  
![FirstActivity](/IntentTest/img/捕获.PNG)  
![SecondActivity](/IntentTest/img/捕获1.PNG) 

###隐式Intent
使用隐式Intent。不仅仅可以启动自己程序内的活动，还可以启动其他的活动，比如你的应用程序需要展示一个网页，当点  
击FirstActively界面的按钮就可以开打系统的浏览器    
![网页](/IntentTest/img/捕获2.PNG)   

通过<intent-filer>中配置当前活动能够响应action，且<data>标签中我们通过Android：scheme 指定了数据的协议必须是http协议，  
这样ThirdActivity应该就像浏览器应用，能够响应打开一个网页的Intent  
![select](/IntentTest/img/捕获3.PNG)   

除了HTTP协议外，我们还可以指定其他的协议吧，比如拨打电话  
![phone](/IntentTest/img/捕获4.PNG)

我们还也可以在Intent启动活动的时候传递数据，将想要传递的数据暂存在Intent中，启动了另一个活动后，只需要把这些数  
据再从Intent中取出,从而实现向下一个活动传递数据    
![TransferData1](/IntentTest/img/捕获5.PNG)  

当然我们也可以将数据返回给上一个活动  

![TransferData2](/IntentTest/img/捕获6.PNG)  

