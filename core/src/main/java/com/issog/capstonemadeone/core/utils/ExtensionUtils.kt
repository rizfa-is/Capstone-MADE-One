package com.issog.capstonemadeone.core.utils


fun Int?.orDefault() = this ?: -1

fun Long?.orDefault() = this ?: 0L

fun Double?.orDefault() = this ?: 0.0

fun Boolean?.orDefault() = this ?: false

fun <T> T?.notNull() = this != null

fun <T> T?.isNull() = this == null