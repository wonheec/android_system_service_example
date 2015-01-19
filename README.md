<div id="table-of-contents">
<h2>Table of Contents</h2>
<div id="text-table-of-contents">
<ul>
<li><a href="#sec-1">1. Android System Service IPC Example</a>
<ul>
<li><a href="#sec-1-1">1.1. How to install</a>
<ul>
<li><a href="#sec-1-1-1">1.1.1. Under AOSP</a></li>
<li><a href="#sec-1-1-2">1.1.2. logcat monitoring</a></li>
<li><a href="#sec-1-1-3">1.1.3. With Android SDK &amp; NDK</a></li>
</ul>
</li>
<li><a href="#sec-1-2">1.2. Running</a></li>
</ul>
</li>
</ul>
</div>
</div>

# Android System Service IPC Example<a id="sec-1" name="sec-1"></a>

In Android system, the main IPC is the **binder**. App can invoke a API
from other app by **binder**, or it can create a **binder** service.

And you can make many system services for customing a ROM. Here we
create many base codes for creating a system service in native code c
level and Java code level.

## How to install<a id="sec-1-1" name="sec-1-1"></a>

**Important**: \* Base environment: Ubuntu 12.04

### Under AOSP<a id="sec-1-1-1" name="sec-1-1-1"></a>

Compile them by AOSP `mm`.

**AOSP should have compiled.**

    # In AOSP topdir
    ## source
    source build/envsetup.sh
    ## lunch
    lunch XXXX
    
    # to source code
    cd android_system_service_example
    # Compile
    mm
    
    # To sync
    adb root # First time only.
    adb remount # First time only.
    adb sync

### logcat monitoring<a id="sec-1-1-2" name="sec-1-1-2"></a>

    adb logcat -v time binder_demo_c_client binder_demo_c_server binder_demo_java_client binder_demo_java_server *:S

### With Android SDK & NDK<a id="sec-1-1-3" name="sec-1-1-3"></a>

System service Binder API is a hack techincal for customing ROM, so the
system binder API invoking is also the hack techincal. Such as
reflecting in Java and linking binder header in native codes.

-   Java code, you can use SDK to compile.

1.  NDK for native code

        #Go to sourcecode
        cd android_system_service_example/NDKBuild
        #Invoke NDK Command
        XXXXXX/bins/android-ndk-r10c/ndk-build
    
    For natice c code, there is not a better way for compiling in NDK
    because of some Binder APIs which are not Supported in NDK. If we want
    to make compiling in NDK, we need to copy a lot of frameworks header
    files. So here I copy headers and shared libraries for compiling as
    demo.
    
    And the environment is:
    
        PLATFORM_VERSION_CODENAME=REL
        PLATFORM_VERSION=4.4
        TARGET_PRODUCT=aosp_mako
        TARGET_BUILD_VARIANT=userdebug
        TARGET_BUILD_TYPE=release
        TARGET_BUILD_APPS=
        TARGET_ARCH=arm
        TARGET_ARCH_VARIANT=armv7-a-neon
        TARGET_CPU_VARIANT=krait
        HOST_ARCH=x86
        HOST_OS=linux
        HOST_OS_EXTRA=Linux-3.2.0-60-generic-x86_64-with-Ubuntu-12.04-precise
        HOST_BUILD_TYPE=release
        BUILD_ID=KRT16S
        OUT_DIR=out

## Running<a id="sec-1-2" name="sec-1-2"></a>

Because of using system API, the program should have **SYSTEM**
permission.

-   Native code: running in **root**
-   Java code: sign by **platfrom certificate**
