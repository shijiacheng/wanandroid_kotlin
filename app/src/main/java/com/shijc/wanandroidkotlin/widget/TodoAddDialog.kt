package com.shijc.wanandroidkotlin.widget

import android.app.DatePickerDialog
import android.os.Bundle
import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.utils.TimeUtils
import java.util.*
import android.widget.ImageButton






/**
 * @Package com.shijc.wanandroidkotlin.widget
 * @Description:
 * @author shijiacheng
 * @date 2019/2/26 下午 1:23
 * @version V1.0
 */
class TodoAddDialog: DialogFragment() {

    private lateinit var spn_from_date:TextView

    var callbackResult: CallbackResult? = null

    fun setOnCallbackResult(callbackResult: CallbackResult) {
        this.callbackResult = callbackResult
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root_view = inflater.inflate(R.layout.dialog_todo_add, container, false)
        (root_view.findViewById(R.id.bt_close) as ImageButton).setOnClickListener { dismiss() }
        (root_view.findViewById(R.id.bt_save) as Button).setOnClickListener {
            sendDataResult()
            dismiss()
        }
        spn_from_date = root_view.findViewById(R.id.spn_from_date)
        spn_from_date.setOnClickListener { dialogDatePickerLight() }
        return root_view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


    private fun sendDataResult() {

        if (callbackResult != null) {
            callbackResult?.sendResult("")
        }
    }


    private fun dialogDatePickerLight() {


        val calendar = Calendar.getInstance()
        var dialog = DatePickerDialog(requireContext(),object :DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date_ship_millis = calendar.getTimeInMillis()
                spn_from_date.setText(TimeUtils.long2String(date_ship_millis,TimeUtils.FORMAT_TYPE_1))
            }
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
            )

        dialog.show()

    }


    interface CallbackResult {
        fun sendResult(obj: Any)
    }
}

