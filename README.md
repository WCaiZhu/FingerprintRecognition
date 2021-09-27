# FingerprintRecognition
有关指纹识别的工具包，适配6.0和9.0以上的安卓设备


Android studio中调用

	implementation "io.github.WCaiZhu:FingerprintRecognitionManager:1.0.0"


方法调用：
```
	private void check() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
       switch (FingerManager.checkSupport(MainActivity.this)) {
          case DEVICE_UNSUPPORTED:
               showToast("您的设备不支持指纹");
          break;
          case SUPPORT_WITHOUT_KEYGUARD:
              //设备支持但未处于安全保护中（你的设备必须是使用屏幕锁保护的，这个屏幕锁可以是password，PIN或者图案都行）
               showOpenSettingDialog("您还未录屏幕锁保护，是否现在开启?");
          break;
          case SUPPORT_WITHOUT_DATA:
               showOpenSettingDialog("您还未录入指纹信息，是否现在录入?");
          break;
          case SUPPORT:
               FingerManager.build().setApplication(getApplication())
                            .setTitle("指纹验证")
                            .setDes("请按下指纹")
                            .setNegativeText("取消")
                            //.setFingerDialogApi23(new MyFingerDialog())//如果你需要自定义android P 以下系统弹窗就设置,注意需要继承
                            BaseFingerDialog，不设置会使用默认弹窗
                            .setFingerCallback(new SimpleFingerCallback() {
                                @Override
                                public void onSucceed() {
                                     showToast("验证成功");
                                }

                                @Override
                                public void onFailed() {
                                    showToast("指纹无法识别");
                                }

                                @Override
                                public void onChange() {
                                    FingerManager.updateFingerData(MainActivity.this);
                                    check();
                                }
                            })
                            .create()
                            .startListener(MainActivity.this);
                    break;
                default:
            }
        }
    }
```
引导录入指纹：
```
   	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    FingerManager.updateFingerData(MainActivity.this);
                    showToast("已同步指纹数据,解除指纹数据变化");
                }
```

同步指纹数据，当有增加或减少指纹时，要进行同步
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
     FingerManager.updateFingerData(MainActivity.this);
    showToast("已同步指纹数据,解除指纹数据变化");
}
```

判断指纹是否发生变化
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    if (FingerManager.hasFingerprintChang(MainActivity.this)) {
         showToast("指纹数据已经发生变化");
     } else {
         showToast("指纹数据没有发生变化");
     }
}
```

