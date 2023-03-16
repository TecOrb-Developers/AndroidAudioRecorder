# AndroidAudioRecording
![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)


This is a simple AndroidAudioRecording library that helps you to record the audio into your apps.

## Features

- You can record the audio.
- After the recoding you can play the audio pause the audio stop and delete the audio.

# How it works:

## Gradle Dependency

-  Add the JitPack repository to your project's build.gradle file

```groovy
    allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
- Add the dependency in your app's build.gradle file

```groovy
    dependencies {
    implementation 'com.github.TecOrb-Developers:AndroidDateTimeUtils:v1.0.4'
}
```

## How to use this library function

### 1. setOnClickListener

```
binding.btnDialog.setOnClickListener{
  AudioRecordView(this,
  this).show()
}
```

### 2. callback

```
overridefunaudioData(saveAudio: String){
  Log.d("saveAudio",
  saveAudio)
}
```

# Developers

MIT License

Copyright (c) 2019 TecOrb Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
