import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WbaHipsterTestModule } from '../../../test.module';
import { LineInfoUpdateComponent } from 'app/entities/line-info/line-info-update.component';
import { LineInfoService } from 'app/entities/line-info/line-info.service';
import { LineInfo } from 'app/shared/model/line-info.model';

describe('Component Tests', () => {
  describe('LineInfo Management Update Component', () => {
    let comp: LineInfoUpdateComponent;
    let fixture: ComponentFixture<LineInfoUpdateComponent>;
    let service: LineInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WbaHipsterTestModule],
        declarations: [LineInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LineInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LineInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LineInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LineInfo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new LineInfo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
