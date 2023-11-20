# LogUtil

1. Add it in your root build.gradle at the end of repositories:

 ```groovy
    dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

 ```groovy
 dependencies {
    implementation 'com.github.data-baker:LogUtil:1.0.0'
    implementation 'com.google.code.gson:gson:2.8.9'
    implementation 'com.squareup.okhttp3:okhttp:4.9.1'
}
 ```

3. Completing initialization in Application

```java
 LogConstants.getInstance().setSignatureString("  ");
 LogConstants.getInstance().setLogUrl(" ");
 LogUtil.openLogUtilStream(this);
 ExceptionHandlerUtil.getInstance().init(this);
```
4. use

```java
   LogUtil.v("测试 配置");
   LogUtil.d("测试 配置");
   LogUtil.w("测试 配置");
   LogUtil.e("测试 配置");
   LogUtil.error("测试 配置");
```


