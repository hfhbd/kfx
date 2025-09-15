package responses

import com.example.Fault

sealed interface BazA {
    class Success(val body: String, val logid: String?) : BazA
    class Error(val body: Fault, val logid: String?) : BazA
}
