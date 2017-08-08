 
# 1.自定义图片选择框架 ImageSelector（一）
##引言：主要写这个图片框架是因为在前段时间，项目出先比较大的问题，就是使用了第三方的图片选择器导致项目不可控制了，所以我抽了点时间来完善自己的图片选择器。
### 1、图片加载用的是Fresco所以请在Application初始化的时候加入
     Fresco.initialize(getApplicationContext());
### 2、接下来配置参数，这里剔一下setMinImageSize这里是选择图片的大小
 

              ImageConfig.getInstance(this)
                .setCamera(true)
                .setMax(9)
                .setMinImageSize(100)
                .action();

### 3、最后就是在当前activity中设置回调

         protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                // show results in textview
                StringBuilder sb = new StringBuilder();
                for(String result : mResults) {
                    sb.append(result).append("\n");
                }
                Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

### 4、还有一些问题没有解决，准备下次一并解决掉，一个是7.0拍照问题，另外一个就是使用jpeglib对图片进行压缩,好处是不会出现oom而且不会出现失帧的情况


