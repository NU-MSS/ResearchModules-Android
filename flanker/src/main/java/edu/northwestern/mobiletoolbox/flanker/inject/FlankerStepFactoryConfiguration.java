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

package edu.northwestern.mobiletoolbox.flanker.inject;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import org.sagebionetworks.research.domain.step.interfaces.Step;
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory;
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory;

import edu.northwestern.mobiletoolbox.flanker.step.instruction_form.FlankerInstructionFormStep;

public class FlankerStepFactoryConfiguration {
    public static class StepConfiguration {

        public final InternalStepViewFactory internalStepViewFactory;

        public final ShowStepFragmentFactory showStepFragmentFactory;

        public final Class<? extends Step> stepType;

        public final String stepTypeKey;

        public StepConfiguration(
                final String stepTypeKey,
                final Class<? extends Step> stepType,
                final InternalStepViewFactory internalStepViewFactory,
                final ShowStepFragmentFactory showStepFragmentFactory) {
            this.stepTypeKey = stepTypeKey;
            this.stepType = stepType;

            this.internalStepViewFactory = internalStepViewFactory;
            this.showStepFragmentFactory = showStepFragmentFactory;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(stepTypeKey, stepType, internalStepViewFactory, showStepFragmentFactory);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final StepConfiguration that = (StepConfiguration) o;
            return Objects.equal(stepTypeKey, that.stepTypeKey) &&
                    Objects.equal(stepType, that.stepType) &&
                    Objects.equal(internalStepViewFactory, that.internalStepViewFactory) &&
                    Objects.equal(showStepFragmentFactory, that.showStepFragmentFactory);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("stepTypeKey", stepTypeKey)
                    .add("stepType", stepType)
                    .add("internalStepViewFactory", internalStepViewFactory)
                    .add("showStepFragmentFactory", showStepFragmentFactory)
                    .toString();
        }
    }

    public static final StepConfiguration FLANKER_INSTRUCTION_STEP =
            new StepConfiguration(
                    FlankerInstructionFormStep.TYPE,
                    FlankerInstructionFormStep.class,
                    FlankerInstructionFormStep.provideInternalStepViewFactory(),
                    FlankerInstructionFormStep.provideStepFragmentFactory()
            );

    public static final ImmutableSet<StepConfiguration> FLANKER_STEP_CONFIGURATIONS =
            ImmutableSet.of(FLANKER_INSTRUCTION_STEP);
}
