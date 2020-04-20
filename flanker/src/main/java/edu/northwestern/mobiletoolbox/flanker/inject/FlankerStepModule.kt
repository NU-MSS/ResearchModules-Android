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
package edu.northwestern.mobiletoolbox.flanker.inject

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import edu.northwestern.mobiletoolbox.flanker.step.form.FlankerFormStep
import edu.northwestern.mobiletoolbox.flanker.step.instruction.FlankerInstructionStep
import edu.northwestern.mobiletoolbox.flanker.step.instruction_form.FlankerInstructionFormStep
import edu.northwestern.mobiletoolbox.flanker.step.overview.FlankerOverviewStep
import org.sagebionetworks.research.domain.inject.StepModule.StepClassKey
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.ShowStepFragmentFactory
import org.sagebionetworks.research.mobile_ui.inject.ShowStepModule.StepViewKey
import org.sagebionetworks.research.presentation.inject.StepViewModule.InternalStepViewFactory
import org.sagebionetworks.research.presentation.inject.StepViewModule.StepTypeKey

@Module
class FlankerInstructionFormStepModule {

    @Provides
    @IntoMap
    @StepClassKey(FlankerInstructionFormStep::class)
    fun provideFlankerInstructionStepClassInfo(): String {
        return FlankerInstructionFormStep.TYPE
    }

    @Provides
    @IntoMap
    @StepTypeKey(FlankerInstructionFormStep.TYPE)
    fun provideInstructionStepViewFactory(): InternalStepViewFactory {
        return FlankerInstructionFormStep.provideInternalStepViewFactory()
    }

    @Provides
    @IntoMap
    @StepViewKey(FlankerInstructionFormStep.TYPE)
    fun provideShowInstructionStepFragmentFactory(): ShowStepFragmentFactory {
        return FlankerInstructionFormStep.provideStepFragmentFactory()
    }
}

@Module
class FlankerFormStepModule {

    @Provides
    @IntoMap
    @StepClassKey(FlankerFormStep::class)
    fun provideFlankerFormStepClassInfo(): String {
        return FlankerFormStep.TYPE
    }

    @Provides
    @IntoMap
    @StepTypeKey(FlankerFormStep.TYPE)
    fun provideFlankerFormStepViewFactory(): InternalStepViewFactory {
        return FlankerFormStep.provideInternalStepViewFactory()
    }

    @Provides
    @IntoMap
    @StepViewKey(FlankerFormStep.TYPE)
    fun provideShowFlankerFormStepFragmentFactory(): ShowStepFragmentFactory {
        return FlankerFormStep.provideStepFragmentFactory()
    }
}

@Module
class FlankerInstructionStepModule {

    @Provides
    @IntoMap
    @StepClassKey(FlankerInstructionStep::class)
    fun provideFlankerInstructionStepClassInfo(): String {
        return FlankerInstructionStep.TYPE
    }

    @Provides
    @IntoMap
    @StepTypeKey(FlankerInstructionStep.TYPE)
    fun provideInstructionStepViewFactory(): InternalStepViewFactory {
        return FlankerInstructionStep.provideInternalStepViewFactory()
    }

    @Provides
    @IntoMap
    @StepViewKey(FlankerInstructionStep.TYPE)
    fun provideShowInstructionStepFragmentFactory(): ShowStepFragmentFactory {
        return FlankerInstructionStep.provideStepFragmentFactory()
    }
}

@Module
class FlankerOverviewStepModule {

    @Provides
    @IntoMap
    @StepClassKey(FlankerOverviewStep::class)
    fun provideFlankerOverviewStepClassInfo(): String {
        return FlankerOverviewStep.TYPE
    }

    @Provides
    @IntoMap
    @StepTypeKey(FlankerOverviewStep.TYPE)
    fun provideFlankerOverviewStepViewFactory(): InternalStepViewFactory {
        return FlankerOverviewStep.provideInternalStepViewFactory()
    }

    @Provides
    @IntoMap
    @StepViewKey(FlankerOverviewStep.TYPE)
    fun provideShowFlankerOverviewStepFragmentFactory(): ShowStepFragmentFactory {
        return FlankerOverviewStep.provideStepFragmentFactory()
    }
}
