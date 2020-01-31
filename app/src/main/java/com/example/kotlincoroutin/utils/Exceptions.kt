package com.example.kotlincoroutin.utils

import java.io.IOException

class ApiException(message : String) : IOException(message)

class NoInternetException(message: String) : IOException(message)