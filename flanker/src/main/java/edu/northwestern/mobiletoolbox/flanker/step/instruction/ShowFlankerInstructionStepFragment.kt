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

package edu.northwestern.mobiletoolbox.flanker.step.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.northwestern.mobiletoolbox.flanker.R
import kotlinx.android.synthetic.main.mtbf_fragment_show_flanker_instruction_step.view.next_button
import kotlinx.android.synthetic.main.mtbf_fragment_show_flanker_instruction_step.view.step_view
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment
import org.sagebionetworks.research.presentation.model.interfaces.StepView

/**
 * A simple [Fragment] subclass.
 * Use the [ShowFlankerInstructionStepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowFlankerInstructionStepFragment : Fragment() {

    protected lateinit var stepView: FlankerInstructionStepView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val arguments = arguments
            if (arguments != null) {
                // noinspection unchecked
                stepView = arguments.getSerializable(ARGUMENT_STEP_VIEW) as FlankerInstructionStepView
            }
        } else {
            // noinspection unchecked
            stepView = savedInstanceState.getSerializable(ARGUMENT_STEP_VIEW) as FlankerInstructionStepView
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.mtbf_fragment_show_flanker_instruction_step, container, false)
        view.step_view.text = stepView.toString()
        view.next_button.setOnClickListener {
            (parentFragment as PerformTaskFragment).goForward()
        }
        return view
    }

    companion object {
        const val ARGUMENT_STEP_VIEW = "edu.northwestern.mobiletoolbox.flanker.step.instruction_form.STEP_VIEW_ARG"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
                stepView: StepView): ShowFlankerInstructionStepFragment {
            val args = Bundle()
            args.putSerializable(ARGUMENT_STEP_VIEW, stepView)

            val fragment = ShowFlankerInstructionStepFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
