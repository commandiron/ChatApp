package com.example.chatapp_by_command.domain.use_case

import com.example.chatapp_by_command.domain.repository.AppRepository

class LoadAcceptedFriendRequestListFromFirebase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.loadAcceptedFriendRequestListFromFirebase()
}