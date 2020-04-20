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

package edu.northwestern.mobiletoolbox.flanker.step.instruction_form

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import edu.northwestern.mobiletoolbox.flanker.step.FlankerInputField
import edu.northwestern.mobiletoolbox.flanker.step.FlankerViewTheme
import edu.northwestern.mobiletoolbox.flanker.step.instruction_form.FlankerInstructionFormStep.Companion.TYPE
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.mapper.DrawableMapper
import org.sagebionetworks.research.presentation.model.interfaces.StepView

data class FlankerInstructionFormStep(
        private val identifier: String = "INVALID_STATE",
        val inputFields: ImmutableList<FlankerInputField> = ImmutableList.of(),
        val flankerImageNames: ImmutableList<String> = ImmutableList.of(),
        val isInstruction: Boolean = false,
        val stepName: String? = null,
        val text: String? = null,
        val viewTheme: FlankerViewTheme? = null
) : Step {

    override fun getType(): String {
        return TYPE
    }

    override fun getIdentifier(): String {
        return identifier
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return ImmutableSet.of()
    }

    override fun copyWithIdentifier(identifier: String): Step {
        return FlankerInstructionFormStep(identifier, inputFields, flankerImageNames, isInstruction, stepName, text,
                viewTheme)
    }

    companion object {
        const val TYPE = "flankerInstructionForm"

        @JvmStatic
        fun provideInternalStepViewFactory(): InternalStepViewFactory {
            return InternalStepViewFactory { step: Step, mapper: DrawableMapper? ->
                FlankerInstructionStepView.create(step as FlankerInstructionFormStep)
            }
        }

        @JvmStatic
        fun provideStepFragmentFactory(): ShowStepFragmentFactory {
            return ShowStepFragmentFactory { stepView: StepView ->
                ShowFlankerInstructionFormStepFragment.newInstance(stepView)
            }
        }
    }
}

data class FlankerInstructionStepView(
        private val identifier: String,
        val inputFields: List<FlankerInputField>,
        val flankerImageNames: List<String>,
        val isInstruction: Boolean,
        val stepName: String?,
        val text: String?,
        val viewTheme: FlankerViewTheme?
) : StepView {

    override fun getIdentifier(): String {
        return identifier
    }

    override fun getType(): String {
        return TYPE
    }

    companion object {
        fun create(flankerInstructionStep: FlankerInstructionFormStep): FlankerInstructionStepView {
            with(flankerInstructionStep) {
                return FlankerInstructionStepView(
                        identifier, inputFields, flankerImageNames, isInstruction, stepName, text, viewTheme)
            }
        }
    }
}
