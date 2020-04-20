/*
 * BSD 3-Clause License
 *
 * Copyright 2020  Sage Bionetworks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1.  Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2.  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3.  Neither the name of the copyright holder(s) nor the names of any contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission. No license is granted to the trademarks of
 * the copyright holders even if such marks are included in this software.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.northwestern.mobiletoolbox.flanker.step.form

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import edu.northwestern.mobiletoolbox.flanker.step.FlankerBranchingNavigationRule
import edu.northwestern.mobiletoolbox.flanker.step.FlankerInputField
import edu.northwestern.mobiletoolbox.flanker.step.FlankerViewTheme
import edu.northwestern.mobiletoolbox.flanker.step.NestedFlankerFormStep
import edu.northwestern.mobiletoolbox.flanker.step.form.FlankerFormStep.Companion.TYPE
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.domain.step.ui.action.Action
import org.sagebionetworks.research.domain.step.ui.action.ActionHandler
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.mapper.DrawableMapper
import org.sagebionetworks.research.presentation.model.interfaces.StepView

data class FlankerFormStep(
        private val identifier: String = "INVALID_STATE",
        private val actions: ImmutableMap<String, Action> = ImmutableMap.of(),
        val branchingNavigationRules: ImmutableList<FlankerBranchingNavigationRule> = ImmutableList.of(),
        val flankerImageNames: ImmutableList<String> = ImmutableList.of(),
        val htmlText: String? = null,
        val inputFields: ImmutableList<FlankerInputField> = ImmutableList.of(),
        val isPractice: Boolean = false,
        val nextStepIdentifier: String? = null,
        val shouldHideActions: ImmutableSet<String> = ImmutableSet.of(),
        val stepBackTo: String? = null,
        val stepGroup: String? = null,
        val stepName: String? = null,
        val steps: ImmutableList<NestedFlankerFormStep> = ImmutableList.of(),
        val text: String? = null,
        val timeout: Int = 0,
        val title: String? = null,
        val viewTheme: FlankerViewTheme? = null
) : Step, ActionHandler {

    override fun getType(): String {
        return TYPE
    }

    override fun getIdentifier(): String {
        return identifier
    }

    override fun getActions(): ImmutableMap<String, Action> {
        return actions
    }

    override fun getHiddenActions(): ImmutableSet<String> {
        return shouldHideActions
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return ImmutableSet.of()
    }

    override fun copyWithIdentifier(identifier: String): Step {
        return FlankerFormStep(identifier, actions, branchingNavigationRules, flankerImageNames, htmlText,
                inputFields, isPractice, nextStepIdentifier, shouldHideActions, stepBackTo, stepGroup,
                stepName, steps, text, timeout, title, viewTheme)
    }

    companion object {
        const val TYPE = "flankerForm"

        @JvmStatic
        fun provideInternalStepViewFactory(): InternalStepViewFactory {
            return InternalStepViewFactory { step: Step, mapper: DrawableMapper? ->
                FlankerFormStepView.create(step as FlankerFormStep)
            }
        }

        @JvmStatic
        fun provideStepFragmentFactory(): ShowStepFragmentFactory {
            return ShowStepFragmentFactory { stepView: StepView ->
                ShowFlankerFormStepFragment.newInstance(stepView)
            }
        }
    }
}

data class FlankerFormStepView(
        private val identifier: String = "INVALID_STATE",
        private val actions: ImmutableMap<String, Action> = ImmutableMap.of(),
        val branchingNavigationRules: ImmutableList<FlankerBranchingNavigationRule> = ImmutableList.of(),
        val flankerImageNames: ImmutableList<String> = ImmutableList.of(),
        val htmlText: String? = null,
        val inputFields: ImmutableList<FlankerInputField>? = ImmutableList.of(),
        val isPractice: Boolean = false,
        val nextStepIdentifier: String? = null,
        val shouldHideActions: ImmutableSet<String> = ImmutableSet.of(),
        val stepBackTo: String? = null,
        val stepGroup: String? = null,
        val stepName: String? = null,
        val steps: ImmutableList<NestedFlankerFormStep> = ImmutableList.of(),
        val text: String? = null,
        val timeout: Int = 0,
        val title: String? = null,
        val viewTheme: FlankerViewTheme? = null
) : StepView {

    override fun getType(): String {
        return TYPE
    }

    override fun getIdentifier(): String {
        return identifier
    }

    companion object {
        fun create(flankerFormStep: FlankerFormStep): FlankerFormStepView {
            with(flankerFormStep) {
                return FlankerFormStepView(identifier, actions, branchingNavigationRules, flankerImageNames, htmlText,
                        inputFields, isPractice, nextStepIdentifier, shouldHideActions, stepBackTo, stepGroup,
                        stepName, steps, text, timeout, title, viewTheme)
            }
        }
    }
}
