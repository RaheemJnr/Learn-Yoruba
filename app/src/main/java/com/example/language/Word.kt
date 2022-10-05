package com.example.language

/**
 * [Word] represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Yoruba translation for that word.
 * it also contain images and audio file
 */
class Word {
    /**
     * Get the default translation for the word
     * (English)
     */
    /**
     * Default translation for the word
     */
    private var mDefaultTranslation: String

    /**
     * Get the Yoruba translation for the word
     */
    private var mYorubaTranslation: String

    /**
     * Get the image for the word
     */
    /**
     * Image resource ID for the word
     */
    private var mimageResourceId = NO_IMAGE_PROVIDED

    /**
     * get the audio for the word
     */
    private var mAudioResourceId: Int = 0


    /**
     * Get the default translation for the word
     */
    fun getDefaultTranslation(): String? {
        return mDefaultTranslation
    }

    /**
     * Get the yoruba translation for the word
     */
    fun getYorubaTranslation(): String? {
        return mYorubaTranslation
    }

    /**
     * Get the image for the word
     */
    fun getImageResourceId(): Int {
        return mimageResourceId
    }

    /**
     * get the audio resource foreach word function
     */
    fun getAudioResource(): Int {
        return mAudioResourceId
    }

    /**@param defaultTranslation is the word in a language that the user is already familiar with
     * (such as English)
     * @param yorubaTranslation   is the word in the Miwok language
     * @param audioResourceId    is the resource ID for the audio file associated with this word
     *
     */
    constructor(defaultTranslation: String, yorubaTranslation: String, audioResourceId: Int ) {
        this.mDefaultTranslation = defaultTranslation
        this.mYorubaTranslation = yorubaTranslation
        this.mAudioResourceId = audioResourceId

    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     * (such as English)
     * @param YorubaTranslation   is the word in the Miwok language
     * @param imageResourceId    is the drawable resource ID for the image associated with the word
     * @param audioResourceId    is the resource ID for the audio file associated with this word
     */
    constructor(
        defaultTranslation: String,
        YorubaTranslation: String,
        imageResourceId: Int,
        audioResourceId: Int
    ) {
        this.mDefaultTranslation = defaultTranslation
        this.mYorubaTranslation = YorubaTranslation
        this.mimageResourceId = imageResourceId
        this.mAudioResourceId = audioResourceId
    }

    /**
     * Returns whether the word has an image associated with it
     *
     * @return boolean
     */
    fun hasImage(): Boolean {
        return mimageResourceId != NO_IMAGE_PROVIDED
    }

    companion object {
        /**
         * Constant value that represents no image was provided for this word
         */
        private const val NO_IMAGE_PROVIDED = -1
    }


}