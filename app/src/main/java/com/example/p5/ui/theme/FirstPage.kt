package com.example.p5.ui.theme

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun HalamanSatu(
    pilihanRasa : List<String>,
    onSelectionChanged : (String) -> Unit,
    onConfirmButtonClicked : (Int) -> Unit,
    onNextButtonClicked : -> Unit,
    onCancelButtonClicked : ->Unit,
    modifier : Modifier = Modifier
)
{
    var rasaYgDipilih by rememberSaveable { mutableStateOf("") }
    var textJmlBeli by rememberSaveable { mutableStateOf("") }

    Column(modifier= modifier,
        verticalArrangement = Arrangement.SpaceBetween){
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))){
            pilihanRasa.forEach{item ->
                Row(modifier=Modifier.selectable(
                    selected = rasaYgDipilih == item,
                    onClick = {
                        rasaYgDipilih = item
                        onSelectionChanged(item)
                    }
                ),
                    verticalAlignment = Alignment.CenterVertically){
                    RadioButton(selected = rasaYgDipilih ==item,
                        onClick= {
                            rasaYgDipilih = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            Row(modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium))
                .weight(if, false),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),){
                OutlinedTextField(
                    value = textJmlBeli,
                    singleLine = true,
                    shape = MaterialTheme.shapes.large,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(150.dp),
                    label = {Text(text = "Jumlah Order")},
                    onValueChange = {textJmlBeli = it}
                )
                Button(modifier = Modifier.weight(if),
                    enabled = textJmlBeli.isNotEmpty(),
                    onClick = {onConfirmButtonClicked(textJmlBeli.toInt()) }
                ){
                    Text(stringResource(R.string.confirm))
                }
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )

        }
    }

}

