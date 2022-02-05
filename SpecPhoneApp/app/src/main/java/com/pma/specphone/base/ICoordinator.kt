package com.pma.specphone.base

interface ICoordinator {
    fun showDetailsFragment(phoneId: Int)
    fun showPhoneList()
    fun showFragmentAboutUs()
    fun showFragmentEmail()
    fun showOldPhones()
    fun showOldPhonesDetailsFragment(phoneId: Int)
}