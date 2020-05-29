package edu.northwestern.mobiletoolbox.serialization.mfs

import edu.northwestern.mobiletoolbox.serialization.MtbStep
import edu.northwestern.mobiletoolbox.serialization.StringIntChoiceInputField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.sagebionetworks.assessmentmodel.*
import org.sagebionetworks.assessmentmodel.resourcemanagement.FileLoader
import org.sagebionetworks.assessmentmodel.resourcemanagement.ResourceInfo
import org.sagebionetworks.assessmentmodel.resourcemanagement.copyResourceInfo
import org.sagebionetworks.assessmentmodel.serialization.NodeContainerObject

@Serializable
@SerialName("MFS_pilot_1a")
data class MFSAssessmentObject(
    override val identifier: String = "",
    @SerialName("steps")
    override val children: List<Node>,
    val sequenceNumbers: List<String?>? = null,
    val taskOrientation: String = "portrait",
    val sequenceLetters: List<String?>? = null,
    //TODO put to the up level MSS if we will not use in json
    override val versionString: String? = null,
    override val estimatedMinutes: Int = 0,
    override val resultIdentifier: String? = null
) : NodeContainerObject(), Assessment {
    override fun createResult(): AssessmentResult = super<Assessment>.createResult()
    override fun unpack(
        fileLoader: FileLoader,
        resourceInfo: ResourceInfo,
        jsonCoder: Json
    ): MFSAssessmentObject {
        imageInfo?.copyResourceInfo(resourceInfo)
        val copyChildren = children.map { it.unpack(fileLoader, resourceInfo, jsonCoder) }
        val copy = copy(children = copyChildren)
        copy.copyFrom(this)
        return copy
    }
}

@Serializable
@SerialName("mfsOverview")
data class MfsOverviewStep(
    override val identifier: String,
    override val fullInstructionsOnly: Boolean = false
) : MtbStep(), InstructionStep

@Serializable
//TODO change value for types
data class MfsForm(
    override val identifier: String,
    val isPractice: Boolean = false,
    @SerialName("inputFields")
    override val children: List<StringIntChoiceInputField> = listOf(),
    //this field is not everywhere
    val sequenceName: String? = null
) : MtbStep(), FormStep