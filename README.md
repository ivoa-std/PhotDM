[![PDF-Preview](https://img.shields.io/badge/PDF-Preview-blue)](https://github.com/ivoa-std/PhotDM/releases/download/auto-pdf-preview/PhotDM-draft.pdf)

# PhotDM
Photometry data model, including description of photometric points links to fluxes, photometric systems and conversions

# Status

Under development.

# Serializations

The `./serialization` folder contains the serializations of the photometric calibrations used by the SED builder [notebook](https://github.com/ivoa/modelinstanceinvot-code/blob/merge-syntax/photdm_impl.ipynb). 
- These serializations are based on the [syntax](https://github.com/ivoa/modelinstanceinvot-code/) still in working daft at the time of writing.
- They include X-Ray enegery bands that are processedas pseudo-filters. 

# Working on this Document

Remember to checkout the repository with its submodules.

    git clone --recurse-submodules https://github.com/ivoa-std/PhotDM.git

Then: run "make" and hope you have all the necessary tools installed.

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
<img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a>
<br />The IVOA Architecture document is licensed under the
<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
Creative Commons Attribution-ShareAlike 4.0 International License</a>.
