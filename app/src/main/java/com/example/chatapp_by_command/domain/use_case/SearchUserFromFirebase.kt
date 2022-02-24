package com.example.chatapp_by_command.domain.use_case

import com.example.chatapp_by_command.domain.repository.AppRepository

class SearchUserFromFirebase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(userEmail: String)
            = repository.searchUserFromFirebase(userEmail)
}