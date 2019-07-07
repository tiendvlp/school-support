package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.fragment.WriteStoryFragmentBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.fragment.WriteStoryFragmentViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class WriteStoryFragment : Fragment(), UploadAvatarEvent {

    private lateinit var mBinding : WriteStoryFragmentBinding
    private lateinit var mViewModel : WriteStoryFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_write_story, container, false)
        mBinding.editor.are.useSoftwareLayerOnAndroid8()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    fun addControls() {}
    fun addEvents() {
        mBinding.btnNext.setOnClickListener {
            val mBundle : Bundle = Bundle()
            mBundle.putString("Content", mBinding.editor.html)
            Navigation.findNavController(it).navigate(R.id.SubmitFragment, mBundle)
        }

        mBinding.btnClose.setOnClickListener {
            Log.d("contentAre", mBinding.editor.html)
            activity?.finish()
        }

        mBinding.editor.toolbar.setOnInsertImageButtonClickListener {
            //            Log.d("firstHTML", mBinding.editor.html )
    //            mBinding.editor.fromHtml("<html><body><img src=\"https://firebasestorage.googleapis.com/v0/b/schoolsupport-22ad2.appspot.com/o/Stories%2FpjX4eCdUONudjNfFjaL1%2Favatar.png?alt=media&token=f71404b1-5543-49e5-8730-3fa8ce76a6a2\" alt=\"Simply Easy Learning\" width=\"200\"" + "height=\"80\">" + "</body></html>")
    //            Log.d("HTMLLast", mBinding.editor.html)
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(context!!, this)
        }
    }
    override fun onUploadSuccess(downloadUri: String) {
        val htmlResult = "<html><body><img src=\"${downloadUri}\"/> \n </body></html>"
        mBinding.editor.fromHtml(htmlResult)
    }

    override fun onUploadFailed(message: String) {
        Log.d("imgThatbai", message)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                var bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, resultUri)
                var id = mViewModel.getStoryId()
                mViewModel.uploadImage(id, bitmap,this, getImageCount())
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.e("ERROR: ", "EditProfileActivity.kt \n onActivityResult \n $error")
            }
        }
    }

    private fun getImageCount () : Int {
        var html:String = mBinding.editor.html
        var regex :String = "<img"
        var lastIndex = 0
        var count = 0
        while (lastIndex != -1) {
            lastIndex = html.indexOf(regex, lastIndex)
            if (lastIndex != -1) {
                count++
                lastIndex += regex.length
            }
        }
        return count
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WriteStoryFragment().apply {

            }
    }
}
