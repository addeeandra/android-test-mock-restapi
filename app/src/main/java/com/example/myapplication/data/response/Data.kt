package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("milestone_created")
	val milestoneCreated: Boolean? = null,

	@field:SerializedName("merchant_processed")
	val merchantProcessed: Boolean? = null,

	@field:SerializedName("milestone_rejected_reason")
	val milestoneRejectedReason: String? = null,

	@field:SerializedName("user_verified_message")
	val userVerifiedMessage: String? = null,

	@field:SerializedName("merchant_rejected_reason")
	val merchantRejectedReason: String? = null,

	@field:SerializedName("milestone_processed")
	val milestoneProcessed: Boolean? = null,

	@field:SerializedName("merchant_verified")
	val merchantVerified: Boolean? = null,

	@field:SerializedName("milestone_verified")
	val milestoneVerified: Boolean? = null,

	@field:SerializedName("user_is_owner")
	val userIsOwner: Boolean? = null,

	@field:SerializedName("user_token")
	val userToken: String? = null,

	@field:SerializedName("merchant_created")
	val merchantCreated: Boolean? = null,

	@field:SerializedName("user_verified")
	val userVerified: Boolean? = null
)