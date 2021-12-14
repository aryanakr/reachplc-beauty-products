package com.reachplc.interview.ui.list

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import okhttp3.internal.concurrent.Task
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
class ListViewModelTest {

    private lateinit var listViewModel: ListViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // TODO: initialise viewmodel

    }
}