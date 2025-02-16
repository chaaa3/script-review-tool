package com.example.scriptreviewtool.model;

/**
 * Enum representing different types of notifications in the script review system.
 */
public enum NotificationType {
    SCRIPT_SUBMITTED,        // When a new script is submitted for review
    REVIEW_REQUESTED,       // When a review is requested
    REVIEW_COMPLETED,       // When a review is completed
    REVISION_REQUESTED,     // When revisions are requested
    REVISION_SUBMITTED,     // When revisions are submitted
    SCRIPT_APPROVED,        // When a script is approved
    SCRIPT_REJECTED,        // When a script is rejected
    COMMENT_ADDED          // When a comment is added to a script
}
