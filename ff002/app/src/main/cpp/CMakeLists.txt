# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
#引入头文件
include_directories(include)

#设置ffmpeg库所在的路径的变量
set(FF ${CMAKE_CURRENT_SOURCE_DIR}/${CMAKE_ANDROID_ARCH_ABI})


add_library( # Sets the name of the library.
        native-lib
        SHARED

        native-lib.cpp)


add_library(avcodec SHARED IMPORTED)

set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${FF}/libavcodec.so)


#avformat
add_library(avformat SHARED IMPORTED)

set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${FF}/libavformat.so)
#swscale
add_library(swscale SHARED IMPORTED)

set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${FF}/libswscale.so)
#avutil
add_library(avutil SHARED IMPORTED)

set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${FF}/libavutil.so)
find_library( # Sets the name of the path variable.
        log-lib


        log)


target_link_libraries( # Specifies the target library.
        native-lib
        avcodec
        avformat
        swscale
        avutil
        android
        ${log-lib})