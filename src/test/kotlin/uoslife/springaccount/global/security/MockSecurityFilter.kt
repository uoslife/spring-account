package uoslife.springaccount.global.security

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class MockSecurityFilter : Filter {
    override fun init(filterConfig: FilterConfig) {}

    override fun doFilter(request: ServletRequest, response: ServletResponse?, chain: FilterChain) {
        val principal =
            object : UserDetails {
                override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                    return mutableListOf()
                }

                override fun getPassword(): String {
                    return ""
                }

                override fun getUsername(): String {
                    return "test"
                }

                override fun isAccountNonExpired(): Boolean {
                    return true
                }

                override fun isAccountNonLocked(): Boolean {
                    return true
                }

                override fun isCredentialsNonExpired(): Boolean {
                    return true
                }

                override fun isEnabled(): Boolean {
                    return true
                }
            }
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities())

        chain.doFilter(request, response)
    }

    override fun destroy() {
        SecurityContextHolder.clearContext()
    }

    fun getFilters(mockHttpServletRequest: MockHttpServletRequest) {}
}
