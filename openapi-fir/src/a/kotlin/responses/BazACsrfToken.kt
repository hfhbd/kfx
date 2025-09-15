package responses

import com.example.Fault

sealed interface BazACsrfToken {
  class Success(val X_CSRF_Token: String) : BazACsrfToken
  class Error(val body: Fault) : BazACsrfToken
}
