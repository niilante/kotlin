/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.project

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.stubindex.KotlinProbablyNothingFunctionShortNameIndex
import org.jetbrains.kotlin.idea.stubindex.KotlinProbablyNothingPropertyShortNameIndex
import org.jetbrains.kotlin.resolve.lazy.ProbablyNothingCallableNames

class ProbablyNothingCallableNamesImpl(project: Project) : ProbablyNothingCallableNames {
    private val functionNames by lazy(LazyThreadSafetyMode.PUBLICATION) {
        KotlinProbablyNothingFunctionShortNameIndex.getInstance().getAllKeys(project)
    }

    private val propertyNames by lazy(LazyThreadSafetyMode.PUBLICATION) {
        KotlinProbablyNothingPropertyShortNameIndex.getInstance().getAllKeys(project)
    }

    override fun functionNames(): Collection<String> = functionNames
    override fun propertyNames(): Collection<String> = propertyNames
}
