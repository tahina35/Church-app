//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

[assembly: global::Xamarin.Forms.Xaml.XamlResourceIdAttribute("cop_mobile.View.ForgotPassword.xaml", "View/ForgotPassword.xaml", typeof(global::cop_mobile.View.ForgotPassword))]

namespace cop_mobile.View {
    
    
    [global::Xamarin.Forms.Xaml.XamlFilePathAttribute("View\\ForgotPassword.xaml")]
    public partial class ForgotPassword : global::Xamarin.Forms.ContentPage {
        
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Xamarin.Forms.Build.Tasks.XamlG", "2.0.0.0")]
        private global::cop_mobile.CustomComponent.CustomEntry userEmail;
        
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Xamarin.Forms.Build.Tasks.XamlG", "2.0.0.0")]
        private global::Xamarin.CommunityToolkit.Behaviors.EmailValidationBehavior validator;
        
        [global::System.CodeDom.Compiler.GeneratedCodeAttribute("Xamarin.Forms.Build.Tasks.XamlG", "2.0.0.0")]
        private void InitializeComponent() {
            global::Xamarin.Forms.Xaml.Extensions.LoadFromXaml(this, typeof(ForgotPassword));
            userEmail = global::Xamarin.Forms.NameScopeExtensions.FindByName<global::cop_mobile.CustomComponent.CustomEntry>(this, "userEmail");
            validator = global::Xamarin.Forms.NameScopeExtensions.FindByName<global::Xamarin.CommunityToolkit.Behaviors.EmailValidationBehavior>(this, "validator");
        }
    }
}
