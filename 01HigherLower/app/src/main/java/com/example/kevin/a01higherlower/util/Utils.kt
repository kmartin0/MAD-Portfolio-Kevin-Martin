package com.example.kevin.a01higherlower.util

import java.util.*


/**
 * Return random number between from (inclusive) and to (exclusive)
 */
fun rand(from: Int, to: Int): Int {
	return Random().nextInt(to - from) + from
}

