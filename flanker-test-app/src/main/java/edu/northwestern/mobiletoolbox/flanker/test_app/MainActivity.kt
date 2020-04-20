package edu.northwestern.mobiletoolbox.flanker.test_app

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.flanker
import org.sagebionetworks.research.domain.repository.TaskRepository
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity
import org.sagebionetworks.research.presentation.model.TaskView
import java.util.UUID
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
//    MSS-mobile-kit

    @Inject
    lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flanker.setOnClickListener {
            launchTask(getString(R.string.mtbf_flanker_task_id), UUID.randomUUID())
        }
    }

    private fun launchTask(taskIdentifier: String,
            taskRunUUID: UUID?) {
        val taskInfoView = taskRepository.getTaskInfo(taskIdentifier).blockingGet()

        //TODO: mapper
        val taskView = TaskView.builder().setIdentifier(taskInfoView.identifier).build()

        val intent = PerformTaskActivity.createIntent(applicationContext, taskView, taskRunUUID)
        this.startActivity(intent)
    }
}
