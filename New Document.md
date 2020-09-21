#活动生命周期观察
Activity类中定义了7个回调方法，覆盖了生命周期的每一个环节  
7个回调函数如下：  
onCreate();  
onStart();  
onResume();  
onPause();  
onStop();  
onDestroy();  
onRestart();  
#程序运行说明
程序有3个Activity，主活动是MainActivity，里面有两个Button分别对应两个活动。  

当运行程序时，MainActivity第一次被创建时会调用onCreate()方法;然后依次执行onStart(); onResume(); 方法    
![启动程序](/img/截图.捕获.png)  
![启动程序](/img/截图.捕获1.png)   
点击第一个Button，启动NormalActivity，分别调用onPause();  onStop();方法  
![普通活动](/img/截图.捕获2.png)   
![普通活动](/img/截图.捕获3.png)  
按back键返回MainActivit，分别调用onRestart();onStart(); onResume();方法    
![返回主活动](/img/截图.捕获4.png)  
再点击第二个Button,启动DialogActivity，分别调用onPause();  
![对话活动](/img/截图.捕获5.png)  
![对话活动](/img/截图.捕获6.png)  
按back键返回MainActivit，调用onResume();方法     
![返回主活动](/img/截图.捕获8.png)  
再按back键退出程序。  
![退出程序](/img/截图.捕获7.png)  

