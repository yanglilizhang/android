**DO NOT** add this module to project if you want to push sources to `github`.
This means do not include `ffmpeg-sdk` module in `settings.gradle.kts` if you push sources to `github`.


**Suggestion**

If you want to develop, you'd better add this module into `settings.gradle.kts` or else the source code won't be highlighted.


**This module can no be imported by other projects.**
If your want to import this module by other projects, you can make a wrapper module just like [adpcm-ima-qt-codec-sdk] and copy any necessary sources form this module to that wrapper project.

### Compile Environment：

- Android Studio: Flamingo | 2022.2.1
- OS：macOS 13.2
- NDK：25.2.9519653
- Min SDK: 23 (Android 6.0)
- FFmpeg 6.0 "Von Neumann"(6.0 was released on 2023-02-27)
- cmake: 3.23.0
- gcc:
  Apple clang version 14.0.0 (clang-1400.0.29.202)
  Target: x86_64-apple-darwin22.3.0
  Thread model: posix
  InstalledDir: /Library/Developer/CommandLineTools/usr/bin
- JDK: java 17.0.6 2023-01-17 LTS

### How to compile ffmpeg and generate so file for Android

You can download from official website and scroll to the **Releases** section:

1. Get ffmpeg source

```shell
% cd /Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/ffmpeg_build
% wget -c https://www.ffmpeg.org/releases/ffmpeg-6.0.tar.xz
```

Unzip it into the following folder:

> -z(gzip), -j(bzip2), -J(xz), --lzma(lzma)

```shell
% cd /Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/ffmpeg_build
% tar xvJf ffmpeg-6.0.tar.xz
```

2. Compile and get static library

```shell
% cd /Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/ffmpeg_build
```

Modify the ffmpeg version in `config.sh` file:

```shell
FFMPEG_FOLDER=ffmpeg-<ffmepg version>
```

Run any one of the following scripts as you want:

```shell
% cd /Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/ffmpeg_build
```

```shell
% ./build_ffmpeg_adpcm_ima_qt_codec.sh
% ./build_ffmpeg_h264_hevc_decoder.sh
% ./build_ffmpeg_adpcm_ima_qt_codec_h264_h265_decoder.sh
```

3. Generate `so` files.
   The above shell script in `Step 2` has already generated `so` files. However, if you want to generate it again,
   in Android Studio, just build project, you will get `so` files.
   Or execute the following command under
   `/Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/jni`
   folder:

```shell
% ndk-build
```

or execute command with full parameters:

```shell
% ndk-build NDK_PROJECT_PATH=/Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/adpcm-ima-qt-sdk/src/main/jni APP_PLATFORM=android-21 NDK_APPLICATION_MK=/Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/jni/Application.mk APP_BUILD_SCRIPT=/Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/jni/Android.mk
```

Then you will get each generate `so` file
in `/Users/yhz61010/AndroidStudioProjects/LeoAndroidBaseUtilProject-Kotlin/ffmpeg-sdk/src/main/libs`
folder.
