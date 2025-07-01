package com.jira

import kotlin.String
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class ClusterStateState {
  @SerialName(value = "STABLE")
  Stable,
  @SerialName(value = "READY_TO_UPGRADE")
  ReadyToUpgrade,
  @SerialName(value = "MIXED")
  Mixed,
  @SerialName(value = "READY_TO_RUN_UPGRADE_TASKS")
  ReadyToRunUpgradeTasks,
  @SerialName(value = "RUNNING_UPGRADE_TASKS")
  RunningUpgradeTasks,
  @SerialName(value = "UPGRADE_TASKS_FAILED")
  UpgradeTasksFailed,
  ;

  override fun toString(): String = serializer().descriptor.getElementName(ordinal)
}
